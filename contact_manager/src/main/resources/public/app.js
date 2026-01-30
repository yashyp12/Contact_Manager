// // ========== Configuration ==========
// const API_BASE_URL = 'http://localhost:7000/api';
//
// // ========== State Management ==========
// let contacts = [];
// let currentEditId = null;
// let deleteContactId = null;
//
// // ========== Initialize App ==========
// document.addEventListener('DOMContentLoaded', function() {
//     loadContacts();
// });
//
// // ========== API Functions ==========
//
// /**
//  * Fetch all contacts from API
//  */
// async function loadContacts() {
//     try {
//         const response = await fetch(`${API_BASE_URL}/contacts`);
//
//         if (!response.ok) {
//             throw new Error('Failed to fetch contacts');
//         }
//
//         contacts = await response.json();
//         renderContacts(contacts);
//         updateStatistics();
//
//     } catch (error) {
//         console.error('Error loading contacts:', error);
//         showNotification('Failed to load contacts', 'error');
//         document.getElementById('contactsTableBody').innerHTML =
//             '<tr><td colspan="6" class="no-contacts">Failed to load contacts. Please try again.</td></tr>';
//     }
// }
//
// /**
//  * Create new contact
//  */
// async function createContact(contactData) {
//     try {
//         const response = await fetch(`${API_BASE_URL}/contacts`, {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(contactData)
//         });
//
//         if (!response.ok) {
//             const error = await response.json();
//             throw new Error(error.error || 'Failed to create contact');
//         }
//
//         showNotification('Contact added successfully!', 'success');
//         loadContacts();
//
//     } catch (error) {
//         console.error('Error creating contact:', error);
//         showNotification(error.message, 'error');
//     }
// }
//
// /**
//  * Update existing contact
//  */
// async function updateContact(id, contactData) {
//     try {
//         const response = await fetch(`${API_BASE_URL}/contacts/${id}`, {
//             method: 'PUT',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(contactData)
//         });
//
//         if (!response.ok) {
//             const error = await response.json();
//             throw new Error(error.error || 'Failed to update contact');
//         }
//
//         showNotification('Contact updated successfully!', 'success');
//         loadContacts();
//
//     } catch (error) {
//         console.error('Error updating contact:', error);
//         showNotification(error.message, 'error');
//     }
// }
//
// /**
//  * Delete contact
//  */
// async function deleteContact(id) {
//     try {
//         const response = await fetch(`${API_BASE_URL}/contacts/${id}`, {
//             method: 'DELETE'
//         });
//
//         if (!response.ok) {
//             const error = await response.json();
//             throw new Error(error.error || 'Failed to delete contact');
//         }
//
//         showNotification('Contact deleted successfully!', 'success');
//         loadContacts();
//
//     } catch (error) {
//         console.error('Error deleting contact:', error);
//         showNotification(error.message, 'error');
//     }
// }
//
// // ========== UI Functions ==========
//
// /**
//  * Render contacts table
//  */
// function renderContacts(contactsToRender) {
//     const tbody = document.getElementById('contactsTableBody');
//
//     if (contactsToRender.length === 0) {
//         tbody.innerHTML = '<tr><td colspan="6" class="no-contacts">No contacts found</td></tr>';
//         return;
//     }
//
//     tbody.innerHTML = contactsToRender.map(contact => `
//         <tr>
//             <td>${contact.id}</td>
//             <td>${contact.firstName} ${contact.lastName || ''}</td>
//             <td>${contact.phone}</td>
//             <td>${contact.email || 'N/A'}</td>
//             <td>${contact.address || 'N/A'}</td>
//             <td>
//                 <div class="action-buttons">
//                     <button class="btn btn-primary btn-small"
//                             onclick="editContact(${contact.id})">
//                         ✏️ Edit
//                     </button>
//                     <button class="btn btn-danger btn-small"
//                             onclick="showDeleteModal(${contact.id})">
//                         🗑️ Delete
//                     </button>
//                 </div>
//             </td>
//         </tr>
//     `).join('');
// }
//
// /**
//  * Update statistics
//  */
// function updateStatistics() {
//     document.getElementById('totalContacts').textContent = contacts.length;
// }
//
// /**
//  * Search contacts
//  */
// function searchContacts() {
//     const searchTerm = document.getElementById('searchInput').value.toLowerCase();
//
//     if (searchTerm === '') {
//         renderContacts(contacts);
//         return;
//     }
//
//     const filtered = contacts.filter(contact => {
//         const fullName = `${contact.firstName} ${contact.lastName || ''}`.toLowerCase();
//         const phone = contact.phone.toLowerCase();
//         const email = (contact.email || '').toLowerCase();
//
//         return fullName.includes(searchTerm) ||
//                phone.includes(searchTerm) ||
//                email.includes(searchTerm);
//     });
//
//     renderContacts(filtered);
// }
//
// // ========== Modal Functions ==========
//
// /**
//  * Show add contact modal
//  */
// function showAddModal() {
//     currentEditId = null;
//     document.getElementById('modalTitle').textContent = 'Add New Contact';
//     document.getElementById('contactForm').reset();
//     document.getElementById('contactModal').style.display = 'block';
// }
//
// /**
//  * Edit contact
//  */
// function editContact(id) {
//     const contact = contacts.find(c => c.id === id);
//
//     if (!contact) return;
//
//     currentEditId = id;
//     document.getElementById('modalTitle').textContent = 'Edit Contact';
//     document.getElementById('firstName').value = contact.firstName;
//     document.getElementById('lastName').value = contact.lastName || '';
//     document.getElementById('phone').value = contact.phone;
//     document.getElementById('email').value = contact.email || '';
//     document.getElementById('address').value = contact.address || '';
//
//     document.getElementById('contactModal').style.display = 'block';
// }
//
// /**
//  * Save contact (create or update)
//  */
// function saveContact(event) {
//     event.preventDefault();
//
//     const contactData = {
//         firstName: document.getElementById('firstName').value.trim(),
//         lastName: document.getElementById('lastName').value.trim() || null,
//         phone: document.getElementById('phone').value.trim(),
//         email: document.getElementById('email').value.trim() || null,
//         address: document.getElementById('address').value.trim() || null
//     };
//
//     if (currentEditId) {
//         // Update existing contact
//         contactData.id = currentEditId;
//         updateContact(currentEditId, contactData);
//     } else {
//         // Create new contact
//         createContact(contactData);
//     }
//
//     closeModal();
// }
//
// /**
//  * Close contact modal
//  */
// function closeModal() {
//     document.getElementById('contactModal').style.display = 'none';
//     document.getElementById('contactForm').reset();
//     currentEditId = null;
// }
//
// /**
//  * Show delete confirmation modal
//  */
// function showDeleteModal(id) {
//     const contact = contacts.find(c => c.id === id);
//
//     if (!contact) return;
//
//     deleteContactId = id;
//     document.getElementById('deleteContactName').textContent =
//         `${contact.firstName} ${contact.lastName || ''} (${contact.phone})`;
//     document.getElementById('deleteModal').style.display = 'block';
// }
//
// /**
//  * Confirm delete
//  */
// function confirmDelete() {
//     if (deleteContactId) {
//         deleteContact(deleteContactId);
//         closeDeleteModal();
//     }
// }
//
// /**
//  * Close delete modal
//  */
// function closeDeleteModal() {
//     document.getElementById('deleteModal').style.display = 'none';
//     deleteContactId = null;
// }
//
// /**
//  * Show notification
//  */
// function showNotification(message, type) {
//     const notification = document.getElementById('notification');
//     notification.textContent = message;
//     notification.className = `notification ${type}`;
//     notification.style.display = 'block';
//
//     setTimeout(() => {
//         notification.style.display = 'none';
//     }, 3000);
// }
//
// // ========== Close modal when clicking outside ==========
// window.onclick = function(event) {
//     const contactModal = document.getElementById('contactModal');
//     const deleteModal = document.getElementById('deleteModal');
//
//     if (event.target === contactModal) {
//         closeModal();
//     }
//
//     if (event.target === deleteModal) {
//         closeDeleteModal();
//     }
// }